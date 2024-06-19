function idCheck(){
	//alert("b");
	if(regForm.id.value === ""){
		alert("id를 입력하시오");
		regForm.id.focus();
	}else{
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url, "id", "width=300, height=150, top=200, left=200");
	}
}