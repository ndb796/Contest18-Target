var feature=new Object ();

// when mapping total lines to fxo and fxs, fxo first, than fxs.
// Eg: if we have 3 lines, 1 fxo, 2 fxs, then we enable fxo0, fxs1, fxs2

// feature_line_init() is called before html document obj is created
// thus the document.write in html can refer variables defined in feature.js
// ps: this routine is called in the included feature.htm
function feature_line_init()
{
	var fxs_no=0;
	var fxo_no=0;    
	var s, m, i;

	feature["fxs0"] = feature["fxs1"] = feature["fxs2"] = feature["fxs3"] = 0;
	feature["fxo0"] = feature["fxo1"] = feature["fxo2"] = feature["fxo3"] = 0;
	s = feature["line_order"];
	for (i=0; i<4; i++) {
		if (/(\S+)/.test(s) == false) {
			feature["line"+i] = 0;
		} else {
			s = s.replace(/(\S+)/, " ");
			m = RegExp.$1;
			feature["line"+i] = m;
			if (m == "FXS"){
				feature["fxs"+fxs_no]="line"+i;
				fxs_no++;
			} else if(m == "FXO"){ 
				feature["fxo"+fxo_no]="line"+i;
				fxo_no++;
			}
		}
	}
	feature["line_total"] = fxs_no+fxo_no;
}

// below routines are called after html document obj is created

function feature_line_is_enabled(no)
{
	return (feature["line_total"] > no);
}

function feature_line_name(no)
{
	var i;
	for(i=0; i<4; i++)
	    if(feature["fxs"+i] == "line"+no)
		return ("FXS "+i)
	for(i=0; i<4; i++)
	    if(feature["fxo"+i] == "line"+no)
		return ("FXO "+i)
}
    
function check_feature(s)
{
	var m;

	s=s.replace(/\s+/g, '');
	if (feature[s]!=null) {			// aaa
		if (feature[s]!=0)
			return 1;
	} else if (/\(([^\(\)]+)\)/.test(s)) {// aaa(bbb)ccc
		m=check_feature(RegExp.$1);
		s=s.replace(/\(([^\(\)]+)\)/, m);
		return check_feature(s);
	} else if (/^(\S+)\|(\S+)$/.test(s)) {	// aaa|bbb
		m = RegExp.$2;
		if(check_feature(RegExp.$1) || check_feature(m))
			return 1;
	} else if (/^(\S+)&(\S+)$/.test(s)) {	// aaa&bbb
		m = RegExp.$2;
		if (check_feature(RegExp.$1) && check_feature(m))
			return 1;
	} else if (/^(\S+)==(\S+)$/.test(s)) {	// aaa==bbb
		if (feature[RegExp.$1]==RegExp.$2)
			return 1;
	} else if (/^(\S+)!=(\S+)$/.test(s)) {	// aaa!=bbb
		if (feature[RegExp.$1]!=RegExp.$2)
			return 1;
	} else if (/^!(\S+)$/.test(s)) {	// !aaa
		if (check_feature(RegExp.$1))
			return 0;
		else
			return 1;
	} else if (s!=0) {	// for 1 or unrecognized token 
		return 1;
	}
	return 0;
}

// check if this feature is enabled
function feature_is_enabled(e)
{
	var s=e.getAttribute("feature");
	if (typeof s == "string" && s.length > 0) {
		if(check_feature(s) == 0)
		    return 0;
	}
	return 1;
}

// check if user is allowed to access an element
function ac_is_allowed(e)
{
	var s=e.getAttribute("ac");
	if (typeof s == "string" && s.length > 0) {
		if (s == "admin" && ac!="admin") {
			return 0;
		}
		if (s == "user" && ac!="admin" && ac!="user") {
			return 0;
		}
	}
	return 1;
}

// check if user is allowed to do input
function input_is_allowed(e)
{
	var s=e.getAttribute("input_ac");
	if (typeof s == "string" && s.length > 0) {
		if (s == "admin" && ac!="admin") {
			return 0;
		}
		if (s == "user" && ac!="admin" && ac!="user") {
			return 0;
		}
	}
	return 1;
}

/* hidden items of disabled or disallowed features */
function feature_init()
{
	var elist, i, n;
	
	elist = (document.all)? document.all : document.body.getElementsByTagName("*");
	for (i=0; i<elist.length; i++) {
		if (feature_is_enabled(elist[i]) && ac_is_allowed(elist[i])) {
			//alert("visible  name="+elist[i].name + ", id="+elist[i].id);
			if (elist[i].className == "data_invisible")
				elist[i].className = "data_visible";
		} else {
			//alert("name="+elist[i].name + ", id="+elist[i].id);
			elist[i].className = "data_invisible";
		}
		if (!input_is_allowed(elist[i]))
			elist[i].disabled=1;
	}

	if (reload_tree) {
		window.open("tree.html?action=tree_page",'tree');
		reload_tree=0;
	}
}

function id_visible(id)
{
	var e=document.getElementById(id);
	if (e == null)
		alert("ERROR: "+id+"  NOT DEFINED?");
	if (feature_is_enabled(e) && ac_is_allowed(e))
		if (e.className == "data_invisible")
			e.className="data_visible";
}

function id_invisible(id)
{
	var e=document.getElementById(id);
	if (e == null)
		alert("ERROR: "+id+"  NOT DEFINED?");
	if (e.className != "feature_disable")
		e.className="data_invisible";
}

