/*
 * @Author: wulong
 * @Date:   2017-03-13 10:22:05
 * @Last Modified by:   wulong
 * @Last Modified time: 2017-04-26 10:24:13
 */
var myApp = angular.module('myApp', [])

    // 通过factory获取json数据，然后返回给各个控制器
    .factory('data',function($q,$http){
        return $http.get('json/swap/jdzt.json');
    })

    .controller('marqueeController',function($scope,data){
        data.success(function () {

        }).then(function (data) {
            $scope.data = data.data;
        })
    })

    .controller('indexController',function ($scope,data) {

    })

    // 红：#d15b47  绿：#87b87f 黄：#ffb752
    .controller('listController', function($scope,data) {
        data.success(function () {

        }).then(function (data) {
            var index = angular.element('#test').text();
            $scope.list = {
                bmData: data.data.jdInfo[index].data
            }
        })
})