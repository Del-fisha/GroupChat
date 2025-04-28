$(function (){
    let getMessageElement = function (message) {
        let item = $('<div class="message-item"></div>');
        let header = $('<div class="message-header"></div>');

        header.append($('<div class="datetime">' + message.date + '</div>'));
        header.append($('<div class="username">' + message.userName + '</div>'));

        let textElement = $('<div class="message"></div>');
        textElement.text(message.text);
        item.append(header);
        item.append(textElement);
        return item;
    }

    let updateMessages = function () {
        $.get('/message', {}, function (response) {
            if (response.length == 0) {
                return;
            }

            // Создаем временный контейнер
            let tempContainer = $('<div>');

            for(let i in response) {
                let element = getMessageElement(response[i]);
                tempContainer.append(element);
            }

            // Заменяем содержимое одним действием
            $('.messages-list').html(tempContainer.html());
        });
    }

    let initApplication = function () {
        $('.messages-and-users').css({display: 'flex'})
        $('.controls').css({display: 'flex'})
        $('.send-message').on('click', function () {
            let message = $('.new-message').val()
            $.post('/message', {message: message}, function (response) {
                if (response.result) {
                    $('.new-message').val('')
                } else {
                    alert('Ошибка! Ещё попытка')
                }
            })
        })

        setInterval(updateMessages, 1000);
        setInterval(updateUsers, 1000);
    }

    let registerUser = function (name) {
        $.post('/auth', {name: name}, function (response) {
            if(response.result) {
                initApplication()
            }
        })
    }

    $.get('/init', {}, function (response) {
        if(!response.result) {
            let name = prompt('Введите имя:')
            registerUser(name)
            return
        }
        initApplication()
    })

    let updateUsers = function () {
        $.get('/users', {}, function (response) {
            if (response.length == 0) {
                return;
            }

            let tempContainer = $('<div>');

            for(let i in response) {
                let user = $('<div class="user-item">' + response[i].name + '</div>');
                tempContainer.append(user);
            }

            $('.users-list').html(tempContainer.html());
        });
    }

})
