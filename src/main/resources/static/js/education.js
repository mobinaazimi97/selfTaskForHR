function loadEducates() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/educates?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const educatesTableContainer = document.getElementById('educates-table-container');
            if (educatesTableContainer) {
                educatesTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

function handleEducateFormSubmit(e) {
    e.preventDefault();

    const educate = {
        id: document.getElementById('educateId').value,
        firstName: document.getElementById('firstName').value.trim(),
        lastName: document.getElementById('lastName').value.trim()
    };

    if (!educate.firstName) {
        alert(document.getElementById('validationErrorText').textContent);
        return;
    }

    fetch('/educates', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(educate)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارسال درخواست');
            }
            return response.json();
        })
        .then(() => {
            const modal = bootstrap.Modal.getInstance(document.getElementById('educateModal'));
            if (modal) {
                modal.hide();
            }
            loadEducates();
        })
        .catch(error => showError(error));
}

function handleEducateDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف سابقه تحصیلی اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const educateId = btn.dataset.id;

    fetch(`/educates/${educateId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف سابقه تحصیلی!!!');
            }
            loadEducates();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initEducateModal() {
    const educateModal = document.getElementById('educateModal');
    if (educateModal) {
        educateModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('educateId').value = btn.dataset.id;
                document.getElementById('firstName').value = row.cells[0].textContent.trim();
                document.getElementById('lastName').value = row.cells[1].textContent.trim();
            } else {
                document.getElementById('educateForm').reset();
                document.getElementById('educateId').value = '';
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
            loadEducates();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadEducates);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadEducates);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleEducateDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadEducates();
    const educateForm = document.getElementById('educateForm');
    if (educateForm) {
        educateForm.addEventListener('submit', handleEducateFormSubmit);
    }

    initEducateModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});

