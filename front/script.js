// Cadastro de lembrete
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
            listarLembretes();
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

// Apresentação de lembretes
function listarLembretes() {
    var container = document.getElementById('exibir-lembretes');
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/lembretes/agrupados', 
        dataType: 'json',
        success: function (lembretes) {
            container.innerHTML = '';

            for (var data in lembretes) {
                var dataHeader = `<h3>${formatarData(data)}</h3>`;
                container.innerHTML += dataHeader;

                lembretes[data].forEach(function (lembrete) {
                    var item =  `<div class="card-lembrete">
                                    <h4 class="lembrete-nome">${lembrete.nome}</h4>
                                    <button class="botao-delete" data-id="${lembrete.id}"><i class="bi bi-trash3"></i></button>
                                </div>`;
                    
                    container.innerHTML += item;
                });
            }

            $('.botao-delete').click(function () {
                var id = $(this).data('id');
                deletarLembrete(id);
            });
        },
        error: function (xhr, status, error) {
            console.error('Erro ao carregar lembretes:', error);
        }
    });
}

function formatarData(data) {
    var partes = data.split('-');
    var ano = parseInt(partes[0]);
    var mes = parseInt(partes[1]) - 1;
    var dia = parseInt(partes[2]);

    var data = new Date(ano, mes, dia);

    data.setDate(data.getDate());

    return data.toLocaleDateString('pt-BR');
}

// Excluir um lembrete
function deletarLembrete(id) {
    $.ajax({
        type: 'DELETE',
        url: `http://localhost:8080/lembretes/${id}`,
        success: function () {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Lembrete excluído com sucesso!',
            }).then(function () {
                listarLembretes();
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Erro:', textStatus, errorThrown);
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Erro ao excluir lembrete',
            });
        }
    });
}

$(document).ready(function () {
    var hoje = new Date().toISOString().split('T')[0];
    document.getElementById('data_do_lembrete').setAttribute('min', hoje);

    listarLembretes();
});
