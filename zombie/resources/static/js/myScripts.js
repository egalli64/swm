function checkUser() {
	let user = document.getElementById("userId");
	if (user.value.length == 0) {
		alert("User name is mandatory!");
		user.focus();
		return false;
	}
	return true;
}
