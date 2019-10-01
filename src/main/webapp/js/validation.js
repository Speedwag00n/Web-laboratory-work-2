const borders = [];
borders["X"] = ["-3", "3"];
borders["Y"] = ["-4", "4"];
borders["R"] = ["2", "5"];

function validate(form) {
	
	let X = form.X.value;
	
	let Y = [];
	for (var i = 0; i < form.Y.length; i++) {
		if (form.Y[i].checked) {
			Y.push(form.Y[i].value);
		}
	}
	
	let R = form.R.value;

	let valid = true;
	
	if (!(isPresented(X, "X", true) && validateTextParam(X, "X", true))) {
		valid = false;
	}
	if (!isPresented(Y, "Y", true)) {
		valid = false;
	}
	if (!(isPresented(R, "R", true) && validateTextParam(R, "R", true))) {
		valid = false;
	}

	return valid;
	
}

function isPresented(param, paramName, warn) {
	if (param == "" || param == null || param.length == 0) {
	    if (warn) {
		    showWarning(paramName + " должен быть указан", paramName);
		}
		return false;
	} else {
		showWarning("", paramName);
	}

	return true;
}

function validateTextParam(param, paramName, warn) {
	if (!(!isNaN( Number(param) ) && param.lastIndexOf('.') != (param.length - 1))) {
	    if (warn) {
		    showWarning(paramName + " должен быть числом", paramName);
		}
		return false;
	}
	let bottomBorder = borders[paramName][0];
	let topBorder = borders[paramName][1];

	if (!(Number(param) > bottomBorder && Number(param) < topBorder)) {
	    if (warn) {
		    showWarning(paramName + " не входит в необходимый диапазон (" + bottomBorder + " ... " + topBorder +")", paramName);
		}
		return false;
	}
	showWarning("", paramName);
	return true;
}

function showWarning(warningMessage, paramName) {
	
	let warningContainer = document.getElementById("warning-container-" + paramName);
	
	if (warningContainer != null) {
		warningContainer.textContent = warningMessage;
	}
	
}