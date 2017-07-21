/*
 * @Author: wulong
 * @Date:   2017-02-15 16:36:36
 * @Last Modified by:   wulong
 * @Last Modified time: 2017-03-27 17:10:11
 */

//建立MySQL连接, 配置数据库信息
var app = require('http').createServer(handler),
    io = require('socket.io').listen(app),
    fs = require('fs'),
    mysql = require('mysql'),
    connectionsArray = [],
    connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: '123456',
        database: 'nodejs',
        port: 3306
    }),
// 轮询间隔
    POLLING_INTERVAL = 1000,
    pollingTimer;

// 检查数据库连接是否正常
connection.connect(function (err) {
    // 不出现错误信息，那表示数据库连接成功
    if (err) {
        console.log("连接数据库失败：", err);
    }
    console.log("连接数据库成功!");
});

//启动HTTP服务，绑定端口8080
app.listen(8888);

// 加载客户端首页
function handler(req, res) {
    fs.readFile(__dirname + '/client.html', function (err, data) {
        if (err) {
            console.log(err);
            res.writeHead(500);
            return res.end('加载客户端首页发生错误...');
        }
        res.writeHead(200);
        res.end(data);
    });
}

/*
 * 这个就是实现主要功能的方法，间隔1秒去查询数据库表，有更新就推送给客户端
 */

var pollingLoop = function () {

    // 查询数据库
    var query = connection.query('SELECT * FROM log_info');
        info = []; // 用于保存查询结果

    // 查询结果监听
    query
        .on('error', function (err) {
            // 查询出错处理
            console.log(err);
            //更新推送
            updateSockets(err);
        })
        .on('result', function (user) {
            // 加入查询到的结果到logInfo数组
            // push方法：在数组的末尾添加新的元素
            // console.log("Info-----:",user);
            info.push(user);
        })
        .on('end', function () {
            // 检查是否有客户端连接，有连接就继续查询数据库
            if (connectionsArray.length) {
                pollingTimer = setTimeout(pollingLoop, POLLING_INTERVAL);
                updateSockets({
                    info: info
                });
            }
        });
};


// 创建一个websocket连接，实时更新数据
io.sockets.on('connection', function (socket) {

    // console.log('当前连接客户端数量:' + connectionsArray.length);
    // 有客户端连接的时候才去查询，不然都是浪费资源
    if (!connectionsArray.length) {
        pollingLoop();
    }

    // 客户端失去连接时触发，参数一：事件名称；参数二：回调函数
    socket.on('disconnect', function () {
        var socketIndex = connectionsArray.indexOf(socket);
        console.log('socket id 为' + socketIndex + '的用户已断开连接！');
        if (socketIndex >= 0) {
            connectionsArray.splice(socketIndex, 1);
        }
        console.log("当前连接客户端数量:"+connectionsArray.length);
    });

    console.log("****************************");
    console.log("*                          *");
    console.log('*     有新的客户端连接!    *');
    connectionsArray.push(socket);
    console.log('*    当前连接客户端数量:' +connectionsArray.length);
    console.log("*                          *");
    console.log("****************************");
});

var updateSockets = function (data) {
    // 加上最新的更新时间
    data.time = new Date();
    // 推送最新的更新信息到所以连接到服务器的客户端
    connectionsArray.forEach(function (tmpSocket) {
        tmpSocket.volatile.emit('notification', data);
    });
};
