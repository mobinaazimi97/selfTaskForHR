function loadUsers() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/users?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const usersTableContainer = document.getElementById('users-table-container');
            if (usersTableContainer) {
                usersTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

async function handleUserFormSubmit(e) {
    try {
        e.preventDefault();

        const user = {
            // id: document.getElementById('userId').value,
            username: document.getElementById('username').value.trim(),
            password: document.getElementById('password').value.trim(),
            locked: document.getElementById('locked').value.trim(),
            roleSet: document.getElementById('roleName').value.trim(),

        };

        if (!user.password) {
            alert(document.getElementById('validationErrorText').textContent);
            return;
        }

        const submitButton = document.getElementById('submitButton');
        let method = 'POST';
        if (submitButton.textContent === 'edit') {
            method = 'PUT';
        }


        const response = await fetch('/users', {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })

        if (!response.ok) {
            throw new Error('خطا در ارسال درخواست');
        }

        const modal = bootstrap.Modal.getInstance(document.getElementById('userModal'));
        if (modal) {
            modal.hide();
        }
        loadUsers();
    } catch (error) {
        showError(error)
    }
}

function handleUserDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف این مشتری اطمینان دارید؟';

    if (!confirm(confirmText)) return;
//todo:id
    const userId = btn.dataset.id;

    fetch(`/users/${userId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف مشتری');
            }
            loadUsers();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initUserModal() {
    const userModal = document.getElementById('userModal');
    if (userModal) {
        userModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('userId').value = btn.dataset.username;
                document.getElementById('password').value = row.cells[0].textContent.trim();
                document.getElementById('roleName').value = row.cells[1].textContent.trim();
                document.getElementById('locked').value = row.cells[2].textContent.trim();
            } else {
                document.getElementById('userForm').reset();
                document.getElementById('userId').value = '';
            }
        });
    }
}

function initPagination() {
    document.querySelectorAll('.page-link').forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelectorAll('.page-item').forEach(item => item.classList.remove('active'));
            this.closest('.page-item').classList.add('active');
            loadUsers();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadUsers);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadUsers);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleUserDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadUsers();

    const userForm = document.getElementById('userForm');
    if (userForm) {
        userForm.addEventListener('submit', handleUserFormSubmit);
    }

    initUserModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});