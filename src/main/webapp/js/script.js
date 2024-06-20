function idCheck(){
	if(regForm.id.value === ""){
		alert("id를 입력하시오");
		regForm.id.focus();
	}else{
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url, "id", "width=300, height=150, top=200, left=200");
	}
}
//로그인 체크 	
function inputCheck(){ 
	if(regForm.id.value === ""){
		alert("id를 입력하시오");
		regForm.id.focus();
		return;
	}else if(regForm.pw.value === ""){
		alert("pw를 입력하시오");
		regForm.pw.focus();
		return;
	}else if(regForm.repw.value === ""){
		alert("비밀번호 검사 해주세요");
		regForm.repw.focus();
		return;
	}else if(regForm.pw.value !== regForm.repw.value){
		alert("비밀번호 불일치");
		regForm.repw.focus();
		return;
	}else if(regForm.uname.value === ""){
		alert("이름을 입력하시오");
		regForm.uname.focus();
		return;
	}else if(regForm.email.value === ""){
		alert("메일 주소를 입력하시오");
		regForm.email.focus();
		return;
	}else if(regForm.email.value === ""){
		alert("메일 주소를 입력하시오");
		regForm.email.focus();
		return;
	}
	regForm.submit();
}