
        document.getElementById("profile-image").addEventListener("click", function () {
            abrirModal();
        });

        function abrirModal() {
            var modal = document.getElementById("image-modal");
            modal.style.display = "block";
        }

        function fecharModal() {
            var modal = document.getElementById("image-modal");
            modal.style.display = "none";
        }
        
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
        