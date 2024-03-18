
const fetchUsername = async() => {
	const response = await fetch('http://localhost:8080/user/name');
	if(response.ok) {
		const {data, status, message} = await response.json();
		console.log(data);
		console.log(status);
		console.log(message);
		console.log(typeof(data));
		console.log(JSON.parse(data));
		console.log(JSON.parse(data).username);
		
		document.getElementById("username").innerHTML = JSON.parse(data).username;
	}
};

const logout = () => window.location.href = '/logout';

// 等待 DOM 加載完畢後再執行
document.addEventListener("DOMContentLoaded", async() => {
	document.getElementById("logout").addEventListener("click", logout);
	
	fetchUsername(); // 取得 username
	
});