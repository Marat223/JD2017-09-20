var nb = nb || {}

nb.utilities = (function() {
	// isArray
	if(typeof Array.isArray === "undefined"){
		Array.isArray = function(arg) {
			return Object.prototype.toString.call(arg) === "[object Array]";
		}
	}
	// get the value for a key from an array of objects
	if(typeof Array.arrayObjectGetValue === "undefined"){
		Array.arrayObjectGetValue = function(array, search) {
			var i = array.length;
			while (i--) {
				if (array[i].key === search) {
					return array[i].value;
				}
			}
		}
	} 
}());

nb.constant = (function() {
	var constants = {},
		ownProp = Object.prototype.hasOwnProperty,
		allowed = {
			string: 1,
			number: 1,
			boolean: 1
		},
		prefix = (Math.random() + "_").slice(2);
	return {
		set: function(name, value) {
			if(this.isDefined(name)){
				return false;
			}
			if(!ownProp.call(allowed, typeof value)){
				return false;
			}
			constants[prefix + name] = value;
			return true;
		},
		isDefined: function(name) {
			return ownProp.call(constants, prefix + name);
		},
		get: function(name) {
			if(this.isDefined(name)) {
				return constants[prefix + name]
			}
		}
	}
}());

// Set constant
	// TRANSPORT FOR LONDON UNIFIED API 
		// App Id
nb.constant.set("TFL_AppID", "b8886385");
		// App Key
nb.constant.set("TFL_AppKEY", "63efa043f33d77aa5b7b2fa7e8ece555");
	
	// Google Maps JavaScript API
		// App Key
nb.constant.set("GMJsAPI_AppKEY", "AIzaSyAEXuyk8ejIYy-b9nHeFyOHGSG5CmvV54A");
	
	// timer, SetTimer max & min
nb.constant.set("TFL_maxTime", 30);
nb.constant.set("TFL_minTime", 0);


nb.userLatLng = {
	testLatLng: [{lat: 51.504850, lon: -0.113698},
				 {lat: 51.514950, lon: -0.120598},
				 {lat: 51.494750, lon: -0.105698}
	],
	getUserLatLng: function (obj, i) {
		if(i >= 0 && i <= 2){
			return $.extend({}, {testLatLng_i: obj.testLatLng[i]});
		}
	}
};

nb.bikePoint = {
	/*
	// Gets the bike point with the given id.
	getBikePoint: function(id) {
		var url = "https://api.tfl.gov.uk/BikePoint/" + id + "?app_id=" + nb.constant.get("TFL_AppID") + "&app_key=" + nb.constant.get("TFL_AppKEY");
		$.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
				//...
            },
            error: function () {
                console.log('error: getBikePoint()');
            }
        });
	},
	*/
	getBikePointsLatLon: function(swLat, swLon, neLat, neLon, callback) {
		var res = false,
			url = "https://api.tfl.gov.uk/BikePoint?swLat=" + swLat + "&swLon=" + swLon + "&neLat=" + neLat + "&neLon=" + neLon + "&app_id=" + nb.constant.get("TFL_AppID") + "&app_key=" + nb.constant.get("TFL_AppKEY");
		$.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            success: function (data) {

				if(typeof callback !== "function"){
					callback = false;
				}
				if(callback){
					callback(data);
				}
            },
            error: function () {
                console.log('error: getBikePointsLatLon()');
            }
        });
	}
};

nb.timer = {
	options: {
		timeMax: 30,			// default
		timeMin: 0,				// default
		timerInterval: 1000,	// default
		tringInterval: 500		// default
	},
	counter: {
		timer: NaN,
		tring: NaN
	},
	setting: function(obj) {
		var timer = obj.counter.timer, 
			tring = obj.counter.tring,
			timerInterval = obj.options.timerInterval,
			tringInterval = obj.options.tringInterval,
			min = 0,
			sec = 0,
			int_0before = function(m) {
				if(m < 10){
					m = "0" + m;
				} else {
					m = m.toString();
				}
				return m;
			},
			// Display
			setDislay = function(m, s) {
				$("#timerDisplay .timer-display-min").html(int_0before(m));
				$("#timerDisplay .timer-display-sec").html(int_0before(s));
			},
			subtract = function(m, s) {
				sec -= 1;
				if(sec < 0){
					min -= 1;
					if(min >= 0){
						sec = 59
					} else { 
						stop(startTring);
					}
				}
			},
			start = function(m, s) {
				if(m !== undefined && s !== undefined){
					//auto start when timer go and window unload
					min = m;
					sec = s;
				}
				else {
					var t = parseInt($("#timerModal .timer-modal-value").text(), 10);
					if(t === NaN  && typeof t !== "number"){
						console.log("Error timer.start parseInt");
						return;
					}
					$('#timerModal').modal('hide');
					min = t;
					sec = 0;
				}
				$("#timerButton").html("STOP");
				clear(timer);
				setDislay(min, sec);
				subtract(min, sec);
				timer = window.setInterval(function(){
					setDislay(min, sec);
					subtract(min, sec);
				}, timerInterval);
			},
			stop = function(callback) {
				clear(timer);
				$("#timerButton").html("START");
				if(typeof callback !== "function"){
					callback = false;
				}
				if(callback){
					callback();
				}
			},
			startTring = function() {
				clear(tring);
				tring = window.setInterval(function(){
					$("#timerDisplay .panel").toggleClass("tringCall");
				}, tringInterval);
			},
			stopTring = function() {
				clear(tring);
				$("#timerDisplay .panel").removeClass("tringCall");
			},
			// Modal (set the timer)
			showModal = function() {
				stop(stopTring);
				setDislay(0, 0);
				$('#timerModal').modal('show');
			},
			setModalDefault = function(m) {
				$('#timerModal .timer-modal-value').html(int_0before(m));
			},
			setModal = function(m) {
				$('#timerModal .timer-modal-value').html(int_0before(m));
			},
			clear = function(t) {
				if(t){
					clearInterval(t);
				}
			};
		return {
			start: start,
			stopTring: stopTring,
			showModal: showModal,
			setModalDefault: setModalDefault,
			setModal: setModal
		}
	},
	events: function(obj) {
		var timeMax = obj.options.timeMax,
			timeMin = obj.options.timeMin,
			time,
			objSet = new obj.setting(obj);
		// When window unload not reset timer
		$(window).on('unload', function() {
			var m = $("#timerDisplay .timer-display-min").text(),
				s = $("#timerDisplay .timer-display-sec").text();
			if(m === '00' && s === '00') return;
			sessionStorage.setItem('autoStartTimer_min', m);
			sessionStorage.setItem('autoStartTimer_sec', s);
			console.log(sessionStorage.getItem('autoStartTimer_min'));
			console.log(sessionStorage.getItem('autoStartTimer_sec'));
		}),
		// Afte unload, getting saved timer data
		$(document).ready(function(){
			console.log(sessionStorage.getItem('autoStartTimer_min'));
			console.log(sessionStorage.getItem('autoStartTimer_sec'));
			var m = sessionStorage.getItem('autoStartTimer_min'),
				s = sessionStorage.getItem('autoStartTimer_sec'),
				mt,
				st;
			if(m && s){
				mt = parseInt(m, 10);
				st = parseInt(s, 10);
					if(mt === NaN && st ===NaN && typeof mt !== "number" && typeof st !== "number"){
						console.log("Error timer.start parseInt");
						return;
					}
				//min = mt;
				//sec = st;
				sessionStorage.removeItem('autoStartTimer_min');
				sessionStorage.removeItem('autoStartTimer_sec');
				objSet.start(mt, st);
			}
		}),
		$(document).delegate("#timerButton", "click", function (e) {
			e.preventDefault();
			objSet.showModal();
			time = timeMax;
			objSet.setModalDefault(time);
		}),
		$(document).delegate("#timerModal .timer-modal-ok", "click", function (e) {
			e.preventDefault();
			objSet.start();
		}),
		$(document).delegate("#timerModal .timer-modal-setUp", "click", function (e) {
			e.preventDefault();
			time +=1;
			if(time > timeMax){
				time = timeMin;
			}
			objSet.setModal(time);
		}),
		$(document).delegate("#timerModal .timer-modal-setDown", "click", function (e) {
			e.preventDefault();
			time -=1;
			if(time < timeMin){
				time = timeMax;
			}
			objSet.setModal(time);
		}),
		$(document).delegate("#timerDisplay", "click", function (e) {
			e.preventDefault();
			objSet.stopTring();
		})
	},
	init: function(obj, options) {
		obj.options = $.extend(obj.options, options);
		obj.events(obj);
	}
}

nb.region = {
	map: {},
	options: {
		lat: 51.50485,
		lon: -0.113698,
		d_lat: 0.0034, // 700 м
		d_lon: 0.0052, // 700 м
		contentString: function( name, bikes, emptyDocks) {
			return '<div class="nbDocks">' + 
					'<div class="nbDockName">' + name + '</div>' + 
					'<div class="nbBikes">bikes available: ' + bikes + '</div>' + 
					'<div class="emptyDocks">spaces: ' + emptyDocks + '</div>' + 
				   '</div>';
		}
	},
	paintData: {
		userLocation: null,
		poligon: null,
		arrCircleBP: [],
		arrInfoWindow: []
	},
	//userLocation: null,
	clearMap: function() {
		var obj = nb.region.paintData;
		// delete old bikePoint and poligon
		if(obj.poligon){
			obj.poligon.setMap(null);
			obj.poligon = null;
		}
		if(obj.arrInfoWindow.length > 0){
			$.each(obj.arrInfoWindow, function (index, o) {
				if(o){
					o.setMap(null);
				}
			});
			obj.arrInfoWindow = [];
		}
		if(obj.arrCircleBP.length > 0){
			$.each(obj.arrCircleBP, function (index, o) {
				if(o){
					google.maps.event.clearListeners(o, 'click');
					o.setMap(null);
				}
			});
			obj.arrCircleBP = [];
		}
		// delete old center (the user's location)
		if(obj.userLocation){
			obj.userLocation.setMap(null);
			obj.userLocation = null;
		}
	},
	drawBikePoints: function(arr) {
		var ob = nb.region,
			opt = nb.region.options,
			pai = nb.region.paintData,
			name,
			bikes, 
			emptyDocks,
			listener = [],
			// Construct the polygon.
			poligonCoords = [
				{lat: opt.lat - opt.d_lat, lng: opt.lon - opt.d_lon},
				{lat: opt.lat - opt.d_lat, lng: opt.lon + opt.d_lon},
				{lat: opt.lat + opt.d_lat, lng: opt.lon + opt.d_lon},
				{lat: opt.lat + opt.d_lat, lng: opt.lon - opt.d_lon},
				{lat: opt.lat - opt.d_lat, lng: opt.lon - opt.d_lon}
			];
			pai.poligon = new google.maps.Polygon({
				paths: poligonCoords,
				strokeColor: '#0000FF',
				strokeOpacity: 0.2,
				strokeWeight: 3,
				fillColor: '#0000FF',
				fillOpacity: 0.02,
				clickable: false
			});
			
		if(Array.isArray(arr) && arr.length > 0){
			$.each(arr, function(index, r){
				pai.arrCircleBP[index] = new google.maps.Circle({
					strokeColor: '#FF0000',
					strokeOpacity: 0.90,
					strokeWeight: 3,
					fillColor: '#0000FF',
					fillOpacity: 0.4,
					map: ob.map,
					center: {lat: r.lat, lng: r.lon},
					radius: 8
				});
				if(Array.isArray(r.additionalProperties)){
					name = r.commonName;
					bikes = Array.arrayObjectGetValue(r.additionalProperties, "NbBikes");
					emptyDocks = Array.arrayObjectGetValue(r.additionalProperties, "NbEmptyDocks");
					pai.arrInfoWindow[index] = new google.maps.InfoWindow({
						content: opt.contentString(name, bikes, emptyDocks),
						position: {lat: r.lat, lng: r.lon}
					});
					listener[index] = new google.maps.event.addListener(pai.arrCircleBP[index], 'click', function(){
						pai.arrInfoWindow[index].open(ob.map, pai.arrCircleBP[index]);
					});
				}
			});	
		}
		pai.poligon.setMap(ob.map);
	},
	drawCenter: function() {
		var ob = nb.region,
			latC = ob.options.lat,
			lngC = ob.options.lon;
			ob.paintData.userLocation = new google.maps.Circle({
				strokeColor: '#0F0FF0',
				strokeOpacity: 0.90,
				strokeWeight: 2,
				fillColor: '#0FAF00',
				fillOpacity: 0.90,
				map: ob.map,
				center: {lat: latC, lng: lngC},
				radius: 7
			});
			ob.map.setCenter({lat: latC, lng: lngC});
	},
	initMap: function () {
		var latCenter = nb.region.options.lat,
			lngCenter = nb.region.options.lon;
		// Create the map.
		nb.region.map = new google.maps.Map(document.getElementById('map'), {
			zoom: 16,
			center: {lat: latCenter, lng: lngCenter},
			mapTypeId: google.maps.MapTypeId.ROADMAP
		});
	},
	scriptMaps: function(callback) {
		var key = nb.constant.get("GMJsAPI_AppKEY");
		return  "<script src='https://maps.googleapis.com/maps/api/js?key=" + key + "&callback="+ callback +"' async defer></script>";
	}
}
nb.mediator = {
	testNumber: 0,
	getBikePointsLatLon: function() {
		var objUser = nb.userLatLng,
			obj = nb.region,
			opt,
			swLat, swLon, neLat, neLon,
			arrUserLatLng = objUser.getUserLatLng(objUser, nb.mediator.testNumber)["testLatLng_i"];
		obj.options = $.extend(obj.options, arrUserLatLng);
		opt = obj.options;
		swLat = opt.lat - opt.d_lat;
		swLon = opt.lon - opt.d_lon;
		neLat = opt.lat + opt.d_lat; 
		neLon = opt.lon + opt.d_lon;
		nb.bikePoint.getBikePointsLatLon(swLat, swLon, neLat, neLon, function(arrbp){
			obj.clearMap();			
			obj.drawCenter();
			obj.drawBikePoints(arrbp);
			
			/* // display all InfoWindow about bikepoint
			for(var i = 0; i < obj.paintData.arrCircleBP.length; i++){
				console.log('i = ' + i);
				google.maps.event.trigger(nb.region.paintData.arrCircleBP[i], 'click', {});
			}
			*/
		});	
	},
	events: function(){
		// Test data - user location
		$(document).delegate("#updateButton", "click", function (e) {
			e.preventDefault();
			if(nb.mediator.testNumber === 2){
				nb.mediator.testNumber = 0;
			}
			else{ 
				nb.mediator.testNumber += 1; 
			}
			nb.mediator.getBikePointsLatLon();
		})	
	},
	init: function(){
		nb.timer.init(nb.timer, {
			timeMax: nb.constant.get("TFL_maxTime"),
			timeMin: nb.constant.get("TFL_minTime"),
			tringInterval: 500,
			timerInterval: 1000
		});
	},
	initMap: function() {
		nb.region.initMap();
		nb.mediator.events();
		nb.mediator.getBikePointsLatLon();
	}
};

$(function() {
	nb.mediator.init();
	$("body").append(nb.region.scriptMaps("nb.mediator.initMap"));
});
