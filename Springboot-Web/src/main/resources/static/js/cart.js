const REMOTE_URL = 'http://localhost:8080';

const fetchUsername = async() => {
	const url = REMOTE_URL + '/user/name';
	const response = await fetch(url);
	if(response.ok) {
		const {data, status, message} = await response.json();
		console.log(data);
		console.log(status);
		console.log(message);
		console.log(typeof(data));
		console.log(JSON.parse(data));
		console.log(JSON.parse(data).username);
		
		$("username").innerHTML = JSON.parse(data).username;
	}
};

const logout = () => window.location.href = '/logout';

const loadHTML = async(filePath, containerId) => {
	const response = await fetch(filePath); // 抓取網頁
	const data = await response.text(); // 網頁內容
	$(containerId).innerHTML = data; // 將網頁內容放到指定容器中
};

// 渲染 products 的函數 
const renderProduct = ({id, name, cost, price, qty}) => `
	<tr>
		<td>${id}</td>
		<td>${name}</td>
		<td>${cost}</td>
		<td>${price}</td>
		<td>${qty}</td>
		<td class="pure-button">B</td>
	</tr>
`;

// 取得指定資源並渲染到指定容器中
const fetchAndRenderData = async(url, containerId, renderFn) => {
	url = REMOTE_URL + url;
	const response = await fetch(url);
	const {status, message, data} = await response.json();
	console.log(status, message, data);
};

const $ = (id) => {
	return document.getElementById(id);
};

// 等待 DOM 加載完畢後再執行
document.addEventListener("DOMContentLoaded", async() => {
	// 1. 配置子網頁
	await loadHTML('products-list.html', 'products-container');
	await loadHTML('orders-list.html', 'orders-container');
	await loadHTML('customers-list.html', 'customers-container');
	
	// 2. 取得預設資源並渲染 
	// 2.1 取得並渲染 username
	fetchUsername();
	// 2.2 取得並渲染 product
	fetchAndRenderData('/products', 'products-body', renderProduct);
	
	// 2.3 取得並渲染 order
	
	// 2.4 取得並渲染 cutomer
	
	// 3. 網頁元件狀態配置
	$("logout").addEventListener("click", logout);
	
});