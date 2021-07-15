function sendit(){
const userpw = document.getElementById('userpw');
const hp = document.getElementById('hp');

const exppwText = /^[0-9]+[A-Z]+[!@#$%^&*]$/;
const expHpText = /^\d{3}\d{3,4}\d{4}$/;

if(!exppwText.test(userpw.value)){
    alert('대문자, 숫자, 특수문자를 꼭 1개이상 포함해주세요.')
    return false;
}
if(!expHpText.test(hp.value)){
    alert('"-"을 제외한 번호 11자리를 입력하세요.')
    return false;
}
return true;
}


