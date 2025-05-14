document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('authForm');
    const phoneInput = form.phone;

    // Автоформат номера
    phoneInput.addEventListener('input', (e) => {
        let input = e.target.value.replace(/\D/g, '');
        input = input.replace(/^7|8/, ''); // убираем первую 7 или 8
        if (!input.startsWith('978')) input = '978' + input;

        let formatted = '+7 (978)';
        if (input.length > 3) formatted += ' ' + input.slice(3, 6);
        if (input.length > 6) formatted += '-' + input.slice(6, 8);
        if (input.length > 8) formatted += '-' + input.slice(8, 10);

        e.target.value = formatted;
    });

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        clearErrors();

        const name = form.username;
        const surname = form.surname;
        const email = form.email;
        const phone = form.phone;
        const password = form.password;

        let valid = true;

        // Имя
        if (name.value.trim().length < 3) {
            showError(name, 'The name must be at least 3 characters long.');
            valid = false;
        }

        // Фамилия
        if (surname.value.trim().length < 5) {
            showError(surname, 'The last name must be at least 5 characters long.');
            valid = false;
        }

        // Телефон
        const phoneDigits = phone.value.replace(/\D/g, '');
        if (phoneDigits.length !== 10) {
            showError(phone, 'The number must contain 10 digits');
            valid = false;
        }

        // Пароль
        const pass = password.value.trim();
        const passRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (!passRegex.test(pass)) {
            showError(password, 'Password: ≥8 characters, Latin, minimum 1 digit');
            valid = false;
        }

        if (valid) {
            form.submit();
        }
    });

    function showError(input, message) {
        input.classList.add('input-error');
        const error = document.createElement('div');
        error.className = 'error-message';
        error.innerText = message;
        input.parentNode.appendChild(error);
    }

    function clearErrors() {
        document.querySelectorAll('.input-error').forEach(el => el.classList.remove('input-error'));
        document.querySelectorAll('.error-message').forEach(el => el.remove());
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("authForm");
    const photoInput = document.getElementById("carPhotos");
    const photoPreview = document.getElementById("photoPreview");

    form.addEventListener("submit", function (event) {
        const name = form.querySelector('input[name="Car Name"]').value.trim();
        const description = form.querySelector('input[name="Description"]').value.trim();
        const price = form.querySelector('input[name="Price"]').value.trim();
        const count = form.querySelector('input[name="Count"]').value.trim();
        const photos = photoInput.files;

        let errors = [];

        if (name.length < 5) errors.push("Название должно быть не меньше 5 символов.");
        if (description.length < 170) errors.push("Описание должно быть минимум 170 символов.");
        if (!price || !count) errors.push("Цена и количество должны быть заполнены.");
        if (photos.length < 5) errors.push("Загрузите минимум 5 фото.");

        if (errors.length > 0) {
            alert(errors.join("\\n"));
            event.preventDefault();
        }
    });

    photoInput.addEventListener("change", function () {
        photoPreview.innerHTML = "";
        Array.from(this.files).forEach(file => {
            const img = document.createElement("img");
            img.src = URL.createObjectURL(file);
            photoPreview.appendChild(img);
        });
    });

    // Function to validate form
    function validateForm(form) {
        const name = form.querySelector('input[name="Car Name"]').value.trim();
        const description = form.querySelector('textarea[name="Description"]').value.trim();
        const price = form.querySelector('input[name="Price"]').value.trim();
        const count = form.querySelector('input[name="Count"]').value.trim();
        const photos = form.querySelector('input[type="file"]').files;

        let errors = [];

        // Name validation
        if (name.length < 3) {
            errors.push("Name must be at least 3 characters long");
        }

        // Description validation
        if (description.length < 150) {
            errors.push("Description must be at least 150 characters long");
        }

        // Price validation
        if (!/^\d+$/.test(price) || parseInt(price) < 500) {
            errors.push("Price must be at least 500 and contain only digits");
        }

        // Count validation
        if (!/^\d+$/.test(count) || parseInt(count) < 1) {
            errors.push("Count must be at least 1 and contain only digits");
        }

        // Photo validation
        if (photos.length === 0) {
            errors.push("Please upload at least one photo");
        }

        return errors;
    }

    // Function to show error messages
    function showErrors(errors) {
        const errorContainer = document.createElement('div');
        errorContainer.className = 'error-messages';
        errorContainer.style.color = 'red';
        errorContainer.style.marginBottom = '1rem';
        errorContainer.style.padding = '1rem';
        errorContainer.style.border = '1px solid red';
        errorContainer.style.borderRadius = '4px';

        errors.forEach(error => {
            const errorMessage = document.createElement('p');
            errorMessage.textContent = error;
            errorContainer.appendChild(errorMessage);
        });

        // Remove any existing error messages
        const existingErrors = document.querySelector('.error-messages');
        if (existingErrors) {
            existingErrors.remove();
        }

        // Add new error messages
        const form = document.querySelector('form');
        form.insertBefore(errorContainer, form.firstChild);
    }

    // Add validation to create car form
    const createForm = document.getElementById('CreaterForm');
    if (createForm) {
        createForm.addEventListener('submit', function(e) {
            const errors = validateForm(this);
            if (errors.length > 0) {
                e.preventDefault();
                showErrors(errors);
            }
        });
    }

    // Add validation to update car form
    const updateForm = document.querySelector('.create_car_form form');
    if (updateForm) {
        updateForm.addEventListener('submit', function(e) {
            const errors = validateForm(this);
            if (errors.length > 0) {
                e.preventDefault();
                showErrors(errors);
            }
        });
    }

    // Photo preview functionality
    const photoInputs = document.querySelectorAll('input[type="file"]');
    photoInputs.forEach(input => {
        input.addEventListener('change', function() {
            const preview = this.nextElementSibling;
            if (preview && preview.classList.contains('photo-preview')) {
                preview.innerHTML = '';
                Array.from(this.files).forEach(file => {
                    const img = document.createElement('img');
                    img.src = URL.createObjectURL(file);
                    img.style.width = '100px';
                    img.style.height = '100px';
                    img.style.objectFit = 'cover';
                    img.style.margin = '5px';
                    preview.appendChild(img);
                });
            }
        });
    });
});
