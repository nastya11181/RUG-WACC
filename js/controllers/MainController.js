app.controller('MainController', ['$scope', function($scope) {
  $scope.bikes = [
	  {
	    id: 1001,
	    lat: 53.22,
		lng: 6.49,
	  },

	  {
	    id: 1002,
	    lat: 53.24,
		lng: 6.581,
	  },

	  {
	    id: 1003,
	    lat: 53.20,
		lng: 6.584,
	  },

	  {
	    id: 1004,
	    lat: 53.18,
		lng: 6.57,
	  },

	  {
	    id: 1005,
	    lat: 53.235,
		lng: 6.57,
	  }

	];
  var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(40.0000, -98.0000),
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    $scope.markers = [];

    var infoWindow = new google.maps.InfoWindow();

    var createMarker = function (info){

        var marker = new google.maps.Marker({
            map: info.map,
            position: new google.maps.LatLng(info.lat, info.lng),
            title: info.id
        });
        marker.content = '<div class="infoWindowContent">' + info.id + '</div>';

        google.maps.event.addListener(marker, 'click', function(){
            infoWindow.setContent('<h2>' + marker.content + '</h2>');
            infoWindow.open($scope.map, marker);
        });

        $scope.markers.push(marker);

    }

    for (i = 0; i < $scope.bikes.length; i++){
        createMarker($scope.bikes[i]);
    }

    $scope.openInfoWindow = function(e, selectedMarker){
        e.preventDefault();
        google.maps.event.trigger(selectedMarker, 'click');
    }
}]);
