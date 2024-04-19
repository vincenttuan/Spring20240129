document.addEventListener('DOMContentLoaded', function() {
    // 同時更新下拉顯單和訂單列表的函數
    updateBentosAndOrders();
});

// 同時更新下拉顯單和訂單列表的函數
async function updateBentosAndOrders() {
	await fetchBentos();
	await fetchOrders();
}

async function fetchBentos() {
    const response = await fetch('/bento');
    const bentos = await response.json();
    const select = document.getElementById('bentoSelect');
    select.innerHTML = '';  // 清空下拉式選單
    bentos.forEach(bento => {
        const option = document.createElement('option');
        option.value = bento.id;
        option.textContent = `${bento.name}/${bento.quantity} - $${bento.price}`;
        select.appendChild(option);
    });
}

async function fetchOrders() {
    const response = await fetch('/order/bento');
    const orders = await response.json();
    const list = document.getElementById('orderList');
    list.innerHTML = '';  // 清空列表
    orders.forEach(order => {
        const item = document.createElement('li');
        // 使用 innerHTML 插入包含HTML元素的字符串
        item.innerHTML = `訂購了 ${order.amount} 個 ${order.bento.name} 便當，共 $${order.amount * order.bento.price}。<a href="#" onclick="deleteOrder(${order.id})">刪除</a>`;
        list.appendChild(item);
    });
    
    // 計算總價格
    const totalPrice = orders.reduce((total, order) => total + order.amount * order.bento.price, 0);
    document.getElementById('totalPrice').textContent = `總價格：$${totalPrice}`;
}

// 刪除訂單的函數
async function deleteOrder(orderId) {
    const response = await fetch('/order/bento/' + orderId, {
        method: 'DELETE'
    });
    if (response.ok) {
        alert('訂單已刪除');
        updateBentosAndOrders(); // 重新載入便當和訂單列表更新顯示
    } else {
        alert('刪除失敗，請稍後再試。');
    }
}

async function orderBento() {
    const bentoId = document.getElementById('bentoSelect').value;
    const quantity = document.getElementById('quantity').value;

    const response = await fetch('/order/bento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ bento: { id: bentoId }, amount: parseInt(quantity, 10) })
    });
    if (response.ok) {
        alert('便當訂購成功！');
        updateBentosAndOrders(); // 重新載入便當和訂單列表更新顯示
    } else {
        alert('訂購失敗，請稍後再試。');
    }
}
