function loadPersons() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/persons?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const personsTableContainer = document.getElementById('persons-table-container');
            if (personsTableContainer) {
                personsTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

function handlePersonFormSubmit(e) {
    e.preventDefault();

    const person = {
        id: document.getElementById('personId').value,
        firstName: document.getElementById('firstName').value.trim(),
        lastName: document.getElementById('lastName').value.trim(),
        username: document.getElementById('username').value.trim()
        // email: document.getElementById('email').value.trim(),
        // phoneNumber: document.getElementById('phoneNumber').value.trim()
    };

    if (!person.firstName) {
        alert(document.getElementById('validationErrorText').textContent);
        return;
    }

    fetch('/persons', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(person)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارسال درخواست');
            }
            return response.json();
        })
        .then(() => {
            const modal = bootstrap.Modal.getInstance(document.getElementById('personModal'));
            if (modal) {
                modal.hide();
            }
            loadPersons();
        })
        .catch(error => showError(error));
}

function handlePersonDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف شخص اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const personId = btn.dataset.id;

    fetch(`/persons/${personId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف شخص!!!');
            }
            loadPersons();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initPersonModal() {
    const personModal = document.getElementById('personModal');
    if (personModal) {
        personModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('personId').value = btn.dataset.id;
                document.getElementById('firstName').value = row.cells[0].textContent.trim();
                document.getElementById('lastName').value = row.cells[1].textContent.trim();
                document.getElementById('username').value = row.cells[2].textContent.trim();
                // document.getElementById('email').value = row.cells[1].textContent.trim();
                // document.getElementById('phoneNumber').value = row.cells[1].textContent.trim();

            } else {
                document.getElementById('personForm').reset();
                document.getElementById('personId').value = '';
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
            loadPersons();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadPersons);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadPersons);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handlePersonDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadPersons();
    const personForm = document.getElementById('personForm');
    if (personForm) {
        personForm.addEventListener('submit', handlePersonFormSubmit);
    }

    initPersonModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});
