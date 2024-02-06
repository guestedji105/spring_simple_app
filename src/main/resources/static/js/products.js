// Function to create a new product
function createProduct() {
    var formData = {
    name: document.getElementById('name').value,
    description: document.getElementById('description').value,
    price: document.getElementById('price').value
};

    // Send AJAX request to create a new product
    fetch('/products', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(formData)
})
    .then(response => {
    if (response.ok) {
    // Reload the product list after creating a new product
    fetchProductList();
} else {
    alert('Failed to create product');
}
})
    .catch(error => console.error('Error:', error));
}

    // Function to fetch and update the product list
    function fetchProductList() {
    fetch('/products')
        .then(response => response.json())
        .then(products => {
            var productTable = document.getElementById('productTable');
            productTable.innerHTML = ''; // Clear existing table data

            var headerRow = document.createElement('tr');
            headerRow.innerHTML = `
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Availability</th>
                <th>Actions</th>
            `;
            productTable.appendChild(headerRow);

            products.forEach(product => {
                var row = document.createElement('tr');
                row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.availability}</td>
                        <td>
                            <button onclick="populateEditForm(${product.id})">Edit</button>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                        </td>
                    `;
                productTable.appendChild(row);
            });
        })
        .catch(error => console.error('Error:', error));
}

    // Function to delete a product
    function deleteProduct(id) {
    if (confirm('Are you sure you want to delete this product?')) {
    fetch(`/products/${id}`, {
    method: 'DELETE'
})
    .then(response => {
    if (response.ok) {
    // Reload the product list after deleting the product
    fetchProductList();
} else {
    alert('Failed to delete product');
}
})
    .catch(error => console.error('Error:', error));
}
}

    // Function to populate the edit form with current values
    // Function to populate the edit form with current values
    function populateEditForm(productId) {
    // Fetch product details using AJAX request
    fetch('/products/' + productId)
        .then(response => response.json())
        .then(product => {
            document.getElementById('createProductWrapper').style.display = "none";
            // Populate form fields with current values
            document.getElementById('editId').value = product.id;
            document.getElementById('editName').value = product.name;
            document.getElementById('editDescription').value = product.description;
            document.getElementById('editPrice').value = product.price;

            document.getElementById('editProductWrapper').style.display = "block";
        })
        .catch(error => console.error('Error:', error));
}

    // Function to create a new product or edit an existing product
    function saveProduct() {
    var id = document.getElementById('editId').value;
    var name = document.getElementById('editName').value;
    var description = document.getElementById('editDescription').value;
    var price = document.getElementById('editPrice').value;

    var formData = {
    name: name,
    description: description,
    price: price
};

    var url = id ? '/products/' + id : '/products';
    var method = id ? 'PUT' : 'POST';

    // Send AJAX request to create or edit a product
    fetch(url, {
    method: method,
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(formData)
})
    .then(response => {
    if (response.ok) {
    cleanAndHideEditForm();
    // Reload the product list after creating or editing a product
    fetchProductList();
} else {
    alert('Failed to save product');
}
})
    .catch(error => console.error('Error:', error));
}

    // Function to populate edit form when "Edit" button is clicked
    function editProduct(id) {
    fetch('/products/' + id)
        .then(response => response.json())
        .then(product => {
            populateEditForm(product);
        })
        .catch(error => console.error('Error:', error));
}

function cleanAndHideEditForm() {
    // Reset form fields and button text after successful save
    document.getElementById('editId').value = '';
    document.getElementById('editName').value = '';
    document.getElementById('editDescription').value = '';
    document.getElementById('editPrice').value = '';
    document.getElementById('editProductWrapper').style.display = "none";
    document.getElementById('createProductWrapper').style.display = "block";
}

function cleanCreateForm() {
    document.getElementById('name').value = '';
    document.getElementById('description').value = '';
    document.getElementById('price').value = '';
}

    // Initial load of product list
    fetchProductList();