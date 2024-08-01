$('#cadastrar-lembretes').submit(function (e) {
    e.preventDefault();

    var lembrete = {
        nome: $('#nome_do_lembrete').val(),
        data: $('#data_do_lembrete').val()
    };

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/lembretes',
        data: JSON.stringify(lembrete),
        contentType: 'application/json',
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Lembrete criado com sucesso!',
            }).then(function () {
                $('#cadastrar-lembretes')[0].reset();
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Erro:', textStatus, errorThrown);
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Erro ao criar lembrete',
            });
        }
    });
});