
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

const loadHTML = async(filePath, containerId) => {
	const response = await fetch(filePath); // 抓取網頁
	const data = await response.text(); // 網頁內容
	document.getElementById(containerId).innerHTML = data; // 將網頁內容放到指定容器中
};

// 等待 DOM 加載完畢後再執行
document.addEventListener("DOMContentLoaded", async() => {
	document.getElementById("logout").addEventListener("click", logout);
	
	fetchUsername(); // 取得 username
	await loadHTML('products-list.html', 'products-container');
	await loadHTML('customers-list.html', 'customers-container');
	await loadHTML('orders-list.html', 'orders-container');
	
});