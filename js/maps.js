//Data
var bikes = [
              {
                  id : '1001',
                  desc : 'Bike info 1',
                  lat : 53.225,
                  long : 6.581
              },
              {
                  id : '1002',
                  desc : 'Bike info 2',
                  lat : 53.2,
                  long : 6.6
              },
              {
                  id : '1003',
                  desc : 'Bike info 3',
                  lat : 53.21,
                  long : 6.54
              },
              {
                  id : '1004',
                  desc : 'Bike info 4',
                  lat : 53.232,
                  long : 6.67
              },
              {
                  id : '1005',
                  desc : 'Bike info 5',
                  lat : 53.19,
                  long : 6.6
              }
          ];

          //Angular App Module and Controller
          var sampleApp = angular.module('mapsApp', []);
          sampleApp.controller('MapController', function ($scope) {

              var mapOptions = {
                  zoom: 11,
                  center: new google.maps.LatLng(53.2,6.5),
                  mapTypeId: google.maps.MapTypeId.TERRAIN
              }

              $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

              $scope.markers = [];

              var infoWindow = new google.maps.InfoWindow();

              var createMarker = function (info){

                  var marker = new google.maps.Marker({
                      map: $scope.map,
                      position: new google.maps.LatLng(info.lat, info.long),
                      type: info.desc
                  });
                  marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';

                  google.maps.event.addListener(marker, 'click', function(){
                      infoWindow.setContent('<h2>' + marker.type + '</h2>');
                      infoWindow.open($scope.map, marker);
                  });

                  $scope.markers.push(marker);

              }

              for (i = 0; i < bikes.length; i++){
                  createMarker(bikes[i]);
              }

              $scope.openInfoWindow = function(e, selectedMarker){
                  e.preventDefault();
                  google.maps.event.trigger(selectedMarker, 'click');
              }

          });
/*
app.config(function ($routeProvider){
  $routeProvider
  	.when('/',{
    controller: 'MainController',
    templateUrl: 'views/home.html'
  })
  /*
     .when('/users/:id',{
    controller: 'UserController',
    templateUrl: 'views/user.html'
  })


  .otherwise({
    redirectTo:'/'
  })
})
  */
