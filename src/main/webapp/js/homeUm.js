 $(document).ready(function() {
   
        $(".btn-curtir").click(function() {
            var buttonElement = $(this);
            var idPostagem = buttonElement.data("id-postagem");
            console.log(idPostagem);

            buttonElement.prop("disabled", true);

            $.ajax({
                type: "POST",
                url: "curtir",
                data: { idPostagem: idPostagem },
                success: function(response) {
                    if (response.success) {
              
                        var novoNumeroCurtidas = response.novoNumeroCurtidas;
                        $(".numero-curtidas-" + idPostagem).text(novoNumeroCurtidas);

                        buttonElement.prop("disabled", false);
                    } else {
                 
                        alert('Não foi possível curtir a postagem. Tente novamente mais tarde.');

                        buttonElement.prop("disabled", false);
                    }
                },
                error: function() {
                 
                    alert('Erro ao processar a solicitação. Tente novamente mais tarde.');

                    buttonElement.prop("disabled", false);
                }
            });
        });
    });
    
 
