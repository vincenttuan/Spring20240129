
const fetchUsername = async() => {
	const response = await fetch('http://localhost:8080/user/name');
	if(response.ok) {
		console.log(response);	
	}
};

// 等待 DOM 加載完畢後再執行
document.addEventListener("DOMContentLoaded", async() => {
	fetchUsername(); // 取得 username
});