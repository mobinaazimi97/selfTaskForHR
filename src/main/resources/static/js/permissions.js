function loadPermissions() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/permissions?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const permissionsTableContainer = document.getElementById('permissions-table-container');
            if (permissionsTableContainer) {
                permissionsTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

async function handlePermissionFormSubmit(e) {
    try {
        e.preventDefault();

        const permission = {
            permissionName: document.getElementById('permissionName').value.trim(),
            locked: document.getElementById('locked').value.trim()
        };

        if (!permission.permissionName) {
            alert(document.getElementById('validationErrorText').textContent);
            return;
        }

        const submitButton = document.getElementById('submitButton');
        let method = 'POST';
        if (submitButton.textContent === 'edit') {
            method = 'PUT';
        }


        const response = await fetch('/permissions', {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(permission)
        })

        if (!response.ok) {
            throw new Error('خطا در ارسال درخواست');
        }

        const modal = bootstrap.Modal.getInstance(document.getElementById('permissionModal'));
        if (modal) {
            modal.hide();
        }
        loadPermissions();
    } catch (error) {
        showError(error)
    }
}

function handlePermissionDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const permissionId = btn.dataset.id;

    fetch(`/permissions/${permissionId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف');
            }
            loadPermissions();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initPermissionModal() {
    const permissionModal = document.getElementById('permissionModal');
    if (permissionModal) {
        permissionModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('permissionId').value = btn.dataset.permissionName;
                document.getElementById('locked').value = row.cells[1].textContent.trim();
            } else {
                document.getElementById('permissionForm').reset();
                document.getElementById('permissionId').value = '';
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
            loadPermissions();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadPermissions);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadPermissions);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handlePermissionDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadPermissions();

    const permissionForm = document.getElementById('permissionForm');
    if (permissionForm) {
        permissionForm.addEventListener('submit', handlePermissionFormSubmit);
    }

    initPermissionModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});