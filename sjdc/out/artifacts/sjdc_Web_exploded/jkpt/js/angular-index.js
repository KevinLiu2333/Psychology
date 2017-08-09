/**
 * Author:wulong
 * Email:491925129@qq.com
 * Date: 2017/4/20
 * Time: 9:49
 */
var myApp = angular.module('myApp',[])

    // 通过factory获取json数据，然后返回给各个控制器
    .factory('data',function($q,$http){
        return [$http.get('json/index/indexMessage.json'),$http.get('json/index/data.json'),$http.get('json/index/chartsData.json')];
    })

    .controller('marqueeController',function($scope,data){
            data[0].success(function () {

            }).then(function (data) {
                $scope.data = data.data;
            })
    })

    .controller('dataController',function($scope,data){
        data[1].success(function () {

        }).then(function (data) {
                $scope.data = data.data;

        })
    });