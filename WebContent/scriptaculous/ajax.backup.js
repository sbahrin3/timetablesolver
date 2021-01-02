function getPage(url) {
	doAjaxRequest('portal_main_view', '../renderer/' + url);
}
function sendCommand(arg0, arg1, arg2) {
	sendAjax(arg0, arg1, arg2);
}
function sendAjax(arg0, arg1, arg2) {
	
	actionName = arg0;
	if ( arg1 === undefined ) arg1 = '';
	if ( arg2 === undefined ) arg2 = '';
	
	if ( arg1.includes("=") ) {
		qs = arg1;
		target = arg2;
	}
	else if ( !arg1.includes("=") ) {
		target = arg1;
		qs = arg2;
	}
	
	if ( target == '' ) target = 'portal_main_view';
			
	var el = document.getElementById(target); 
	
	el.style.display='block'; 
	var d = document.createElement('div'); 
	d.setAttribute('id', 'div_wait_indicator');
	d.className = 'shadow';
	d.setAttribute('style', 'width:200px;height:200px;top:20%;left:50%;margin-top: -100px;margin-left:-100px;position:fixed;color:#000;background-color:#fff;z-index:9999;text-align:center;padding-top:40px;opacity: 0.5;filter: alpha(opacity=50)');
	d.innerHTML = "Please Wait...<br/><br/><img src='../img/loading1.gif' border='0' width='100px'>";
	d.style.display = 'block';
	el.appendChild(d);
	
	var module_name = document.getElementById('__module_name').value;
   	doAjaxUpdater(document.__main_form, '../div/' + module_name, target, actionName, qs);
   	
   	
   	
}
function doAjaxHTTPRequester(url, target) { 
	new Ajax.Updater(target, url, { 
		method: 'get',  
		evalScripts: true,
		evalJS: true,
		onComplete: function(t) { 
			t.responseText; 
		}, 
		on404: function(t) { 
			alert('error 404'); 
		}, 
		onFailure: function(t) { 
			alert('failure get response'); 
		} 
	}); 
}
function doAjaxRequest(divName, url) { 
	var el = document.getElementById(divName); 
	
	el.innerHTML = ""; 
	el.style.display='block'; 
	var d = document.createElement('div'); 
	d.setAttribute('id', 'div_wait_indicator');
	d.className = 'shadow';
	d.setAttribute('style', 'width:200px;height:200px;top:20%;left:50%;margin-top: -100px;margin-left:-100px;position:fixed;color:#000;background-color:#fff;z-index:9999;text-align:center;padding-top:40px;opacity: 0.5;filter: alpha(opacity=50)');
	d.innerHTML = "Loading...<br/><img src='../img/loading1.gif' border='0' width='100px'>";
	d.style.display = 'block';
	el.appendChild(d);
	
	
	var target = divName; 
	doAjaxHTTPRequester(url, target);
}
function doAjaxPostRequest(divName, url) {
	var el = document.getElementById(divName);el.innerHTML = "";
	el.style.display='block';
	var d = document.createElement('div');
	
	d.setAttribute('id', 'div_wait_indicator');
	d.setAttribute('style', 'position:relative;left:20px;right:100px;width:250px;height:50px;padding-top:15px;text-align:center;font-weight:bold;background:#5ba0d0;border-radius:5px;-moz-box-shadow: 10px 10px 5px #888;-webkit-box-shadow: 10px 10px 5px #888; box-shadow: 10px 10px 5px #888;font-family:Verdana;font-size:12px;color:#fff; ');
	d.innerHTML = "Requesting Page... Please Wait...";
	el.appendChild(d);
	
	
	var target = divName;
	new Ajax.Updater(target, url, {
		method: 'post', 
		evalScripts: true,
		onComplete: function(t) {
			t.responseText;
		},
		on404: function(t) {
			alert('error 404');
		},
		onFailure: function(t) { 
			alert('failure get response');
		}
	});
}


function doAjaxUpdater(objForm, url, target, actionName, qs) {
    var theForm = objForm;
	var pars = "_d=_d";
	var count = theForm.elements.length;
	theForm.command.value = actionName;
	var readparam = false;
	
	//--check for validation
	var is_validated = true;
	for ( var i=0; i < count; i++) {
		var e = theForm.elements[i];
		if ( e.type != "button" ) {
			if ( e.getAttribute("data-validation") == "chars") {
				if ( e.value == "" ) {
					console.log("data is empty... validation failed!");
				}
			}
		}
	}
	
	if ( is_validated ) { 
		for ( var i=0; i < count; i++) {
		   readparam = false;
		   if ( theForm.elements[i].type == "radio" || theForm.elements[i].type == "checkbox") {
			  if ( theForm.elements[i].checked ) {
			     readparam = true;
			  } 
		   } else {
		   	  readparam = true;
		   }
		   
		   if ( readparam ) {
		  	   var id = theForm.elements[i].id;
			   var name = theForm.elements[i].name;
			   if ( name == "" ) theForm.elements[i].name = id;
			   if ( id == "" ) theForm.elements[i].id = name;
		       name = theForm.elements[i].name;
			   var value = theForm.elements[i].value;
		       pars = pars + '&' + name + '=' + encodeURIComponent(value);
	       }
		}
		pars = pars + '&' + qs;
		
	    var myAjax = new Ajax.Updater(target, url, {
					method: 'post', 
					parameters: pars,
					evalScripts: true, 
	  				onComplete: function(t) {
	  				  	result = t.responseText;
	  				  	
					},
		            on404: function(t) {
						alert('error 404');
		            },
		            onFailure: function(t) {
						alert('failure get response');
		            }     											
			});	
    }
	
	else {
		console.log("Can't submit because fields validation has failed!");
	}
}

function doAjaxUpdaterByParamName(objForm, url, target, actionName, qs, paramName) {
    var theForm = objForm;
	var pars = "_d=_d";
	var count = theForm.elements.length;
	theForm.command.value = actionName;
	var readparam = false;
	
	for ( var i=0; i < count; i++) {
	   readparam = false;
	   if ( theForm.elements[i].type == "radio" || theForm.elements[i].type == "checkbox") {
		  if ( theForm.elements[i].checked ) {
		     readparam = true;
		  } 
	   } else {
	   	  readparam = true;
	   }
	  
	   if ( readparam ) {
	  	   var id = theForm.elements[i].id;
		   var name = theForm.elements[i].name;
		   if ( name == "" ) theForm.elements[i].name = id;
		   if ( id == "" ) theForm.elements[i].id = name;
	       name = theForm.elements[i].name;
		   var value = theForm.elements[i].value;
		   if ( name == "command")
			   pars = pars + '&' + name + '=' + encodeURIComponent(value);
		   if ( name == paramName )
			   pars = pars + '&' + name + '=' + encodeURIComponent(value);
	       
       }
	}
	pars = pars + '&' + qs;
    var myAjax = new Ajax.Updater(target, url, {
    											method: 'post', 
    											parameters: pars,
    											evalScripts: true, 
    							  				onComplete: function(t) {
    							  				  	result = t.responseText;
    							  				  	
            									},
									            on404: function(t) {
													alert('error 404');
									            },
									            onFailure: function(t) {
													alert('failure get response');
									            }     											
    											});	
    	 
}


function doAjaxUpdaterChain(objForm, url, target, actionName, qs, target2, actionName2, qs2) {
    var theForm = objForm;
	var pars = '_d=d';
	var count = theForm.elements.length;
	theForm.command.value = actionName;
	var readparam = false;
	
	for ( var i=0; i < count; i++) {
	   readparam = false;
	   if ( theForm.elements[i].type == "radio" || theForm.elements[i].type == "checkbox") {
		  if ( theForm.elements[i].checked ) {
		     readparam = true;
		  } 
	   } else {
	   	  readparam = true;
	   }
	  
	   if ( readparam ) {
	  	   var id = theForm.elements[i].id;
		   var name = theForm.elements[i].name;
		   if ( name == '' ) theForm.elements[i].name = id;
		   if ( id == '' ) theForm.elements[i].id = name;
	       name = theForm.elements[i].name;
		   var value = theForm.elements[i].value;
	       pars = pars + '&' + name + '=' + encodeURIComponent(value);
       }
	}
	pars = pars + '&' + qs;
    var myAjax = new Ajax.Updater(target, url, {
    											method: 'post', 
    											parameters: pars,
    											evalScripts: true,
    							  				onComplete: function(t) {
    							  				  	result = t.responseText;
    							  				  	doAjaxUpdater(objForm, url, target2, actionName2, qs2);
            									},
									            on404: function(t) {
													alert('error 404');
									            },
									            onFailure: function(t) {
													alert('failure get response');
									            }     											
    											});		 
}


function numeralsOnly(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : 
        ((evt.which) ? evt.which : 0));
    if ( charCode == 46 || charCode== 45) return true;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
	
function get_page_size() {
    var xScroll, yScroll;

    if (window.innerHeight && window.scrollMaxY) {	
      xScroll = document.body.scrollWidth;
      yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
      xScroll = document.body.scrollWidth;
      yScroll = document.body.scrollHeight;
    } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
      xScroll = document.body.offsetWidth;
      yScroll = document.body.offsetHeight;
    }

    var windowWidth, windowHeight;
    if (self.innerHeight) {	// all except Explorer
      windowWidth = self.innerWidth;
      windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
      windowWidth = document.documentElement.clientWidth;
      windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
      windowWidth = document.body.clientWidth;
      windowHeight = document.body.clientHeight;
    }	
    
    // for small pages with total height less then height of the viewport
    if(yScroll < windowHeight){
      pageHeight = windowHeight;
    } else { 
      pageHeight = yScroll;
    }

    // for small pages with total width less then width of the viewport
    if(xScroll < windowWidth){	
      pageWidth = windowWidth;
    } else {
      pageWidth = xScroll;
    }

    arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
    return arrayPageSize;
}
	