        var campo = document.getElementById("meuCampo");
        var modal = document.getElementById("meuModal");

        campo.addEventListener("click", abrirModal);

        function abrirModal() {
            modal.style.display = "block";
        }

        function fecharModal() {
            modal.style.display = "none";
        }
        
        
       // function recarregarPagina() {
            //location.reload();
        //}

       
        //var segundos = 30; 

        
        //setTimeout(recarregarPagina, segundos * 1000);
        
    function abrirModalAtualizar(idPostagem) {
        var modalID = "#atualizarModal" + idPostagem;
        $(modalID).modal("show");
    }
    function fecharModalAtualizar(idPostagem){
    	var modalID = "#atualizarModal" + idPostagem;
    	$(modalID).modal("hide");

    }   
    
    function abrirModalDeletar(idPostagem) {
        var modalID = "#deletarModal" + idPostagem;
        $(modalID).modal("show");
    }
    
    function fecharModalDeletar(idPostagem){
    	var modalID = "#deletarModal" + idPostagem;
    	$(modalID).modal("hide");

    }
    