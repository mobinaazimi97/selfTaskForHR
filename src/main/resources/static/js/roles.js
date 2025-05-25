function loadRoles() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/roles?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const rolesTableContainer = document.getElementById('roles-table-container');
            if (rolesTableContainer) {
                rolesTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

async function handleRoleFormSubmit(e) {
    try {
        e.preventDefault();

        const role = {
            roleName: document.getElementById('roleName').value.trim(),
            locked: document.getElementById('locked').value.trim()
        };

        if (!role.roleName) {
            alert(document.getElementById('validationErrorText').textContent);
            return;
        }

        const submitButton = document.getElementById('submitButton');
        let method = 'POST';
        if (submitButton.textContent === 'edit') {
            method = 'PUT';
        }


        const response = await fetch('/roles', {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(role)
        })

        if (!response.ok) {
            throw new Error('خطا در ارسال درخواست');
        }

        const modal = bootstrap.Modal.getInstance(document.getElementById('roleModal'));
        if (modal) {
            modal.hide();
        }
        loadRoles();
    } catch (error) {
        showError(error)
    }
}

function handleRoleDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف این نقش اطمینان دارید؟';

    if (!confirm(confirmText)) return;
//todo:id
    const roleId = btn.dataset.id;

    fetch(`/roles/${roleId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف نقش');
            }
            loadRoles();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initRoleModal() {
    const roleModal = document.getElementById('roleModal');
    if (roleModal) {
        roleModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('roleId').value = btn.dataset.roleName;
                document.getElementById('locked').value = row.cells[1].textContent.trim();
            } else {
                document.getElementById('roleForm').reset();
                document.getElementById('roleId').value = '';
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
            loadRoles();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadRoles);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadRoles);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleRoleDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadRoles();

    const roleForm = document.getElementById('roleForm');
    if (roleForm) {
        roleForm.addEventListener('submit', handleRoleFormSubmit);
    }

    initRoleModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});