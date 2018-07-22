function _change() {

    var pathName = window.document.location.pathname;
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	$("#vCode").attr("src", projectName+"/VerifyCodeCreate?" + new Date().getTime());
}