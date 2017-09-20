//creates a map
function myMap() {
var mapOptions = {
    center: new google.maps.LatLng(53.220, 6.567,),
    zoom: 12,
    mapTypeId: google.maps.MapTypeId.HYBRID
}

var map = new google.maps.Map(document.getElementById("map"), mapOptions);
return map;
};

//show a marker given the map, the coordinates and id (string)
function showMarker(map, x, y, id){
	var new_marker = new google.maps.Marker({
		position: {lat: x, lng: y},
		map: map, 
		title: id
	});
};

//function that is called when we click the button
//shows all bicycles
function showAllBikes(map){
	showMarker(map, 53.22, 6.567, 'id123');
	showMarker(map, 53.24, 6.58, 'id456');
	showMarker(map, 53.21, 6.55, 'id345');
	showMarker(map, 53.20, 6.59, 'id345');
	showMarker(map, 53.21, 6.55, 'id345');
	showMarker(map, 53.235, 6.53, 'id345');
	
}