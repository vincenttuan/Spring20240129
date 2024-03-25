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
		<td><span class="button-add pure-button">B</span></td>
		<td><span class="button-delete pure-button product-button-delete">刪除</span></td>
	</tr>
`;

// 新增 product 的函數 
const addProduct = async() => {
	const url = `${REMOTE_URL}/products`;
	const response = await fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			name: $('product-name-input').value,
			cost: $('product-cost-input').value,
			price: $('product-price-input').value,
			qty: $('product-qty-input').value
		})
	});
	
	const {status, message, data} = await response.json();
	alert(message); 
	// 重新渲染產品列表
	fetchAndRenderData('/products', 'products-body', renderProduct);
};
//-----------------------------------------------------------------

// 渲染 customers 的函數 
// class="pure-button button-delete customer-button-delete"
// pure-button button-delete 給 css 用
// customer-button-delete 給程式判斷使用不是要給 css 用
const renderCustomer = ({id, username, password}) => `
	<tr>
		<td>${id}</td>
		<td>${username}</td>
		<td>${password}</td>
		<td><span data-id="${id}" id="customer-delete-submit" class="pure-button button-delete customer-button-delete">刪除</td>
	</tr>
`;

// 新增 customer 的函數 
const addCustomer = async() => {
	const url = `${REMOTE_URL}/customers`;
	const response = await fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			username: $('customer-username-input').value,
			password: $('customer-password-input').value
		})
	});
	
	const {status, message, data} = await response.json();
	alert(message); 
	// 重新渲染客戶列表
	fetchAndRenderData('/customers', 'customers-body', renderCustomer);
};

// 刪除客戶
const handleDeleteCustomer = async(customerId) => {
	console.log("刪除客戶 id = ", customerId);
	const url = `${REMOTE_URL}/customers/${customerId}`;
	const response = await fetch(url, {
		method: 'DELETE'
	});
	const {status, message, data} = await response.json();
	console.log(status, message, data);
	alert(message);
	// 重新渲染客戶列表
	fetchAndRenderData('/customers', 'customers-body', renderCustomer);
};

//-----------------------------------------------------------------
// 取得指定資源並渲染到指定容器中
const fetchAndRenderData = async(url, containerId, renderFn) => {
	url = REMOTE_URL + url;
	const response = await fetch(url);
	const {status, message, data} = await response.json();
	console.log(status, message, data);
	// data.map(renderFn) 會得到一個數組，其中每個元素是 renderFn 函數的返回值
	// .join('') 用於將數組轉換為字符串給 innerHTML 屬性當作參數使用 
	// Array.isArray(data) 用於判斷 data 是否是數組
	// 如果 data 是數組，則使用 data.map(renderFn).join('') 作為 innerHTML 的參數
	// 如果 data 不是數組，則直接使用 renderFn(data) 作為 innerHTML 的參數
	//$(containerId).innerHTML = data.map(renderFn).join('');
	$(containerId).innerHTML = Array.isArray(data) ? data.map(renderFn).join(',') : data.map(renderFn);
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
	fetchAndRenderData('/customers', 'customers-body', renderCustomer);
	
	// 2.3 取得並渲染 order
	
	// 2.4 取得並渲染 cutomer
	
	// 3. 網頁元件狀態配置
	$("logout").addEventListener("click", logout);
	// 3.1 product 相關元件設定
	// 3.1.1 List | Add UI 切換設定
	$("products-add-table").style.display = "none";
	$("products-list-link").addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		$("products-list-table").style.display = "";
		$("products-add-table").style.display = "none";
	});
	$("products-add-link").addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		$("products-list-table").style.display = "none";
		$("products-add-table").style.display = "";
	});
	// 3.1.2 product 新增元件設定
	$("product-add-submit").addEventListener("click", addProduct);
	// 3.1.3 product 刪除元件設定
	$('products-list-table').addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		console.log(event.target.classList);
	});
	
	// 3.2 customer 相關元件設定
	// 3.2.1 List | Add UI 切換設定
	$("customers-add-table").style.display = "none";
	$("customers-list-link").addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		$("customers-list-table").style.display = "";
		$("customers-add-table").style.display = "none";
	});
	
	$("customers-add-link").addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		$("customers-list-table").style.display = "none";
		$("customers-add-table").style.display = "";
	});
	// 3.2.2 customer 新增元件設定
	$("customer-add-submit").addEventListener("click", addCustomer);
	// 3.2.3 customer 刪除元件設定
	$("customers-list-table").addEventListener("click", (event) => {
		event.preventDefault(); // 取消該元件的預設行為
		//console.log(event.target.classList);
		
		if(event.target.classList.contains('customer-button-delete')) {
			console.log('按下客戶刪除');
			const customerId = event.target.getAttribute('data-id');
			console.log('客戶id:', customerId);
			// 刪除客戶
			handleDeleteCustomer(customerId);
		}	
	});
});