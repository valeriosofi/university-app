(function() {
    'use strict';

    angular
        .module('main')
        .controller('addStudentController',addStudentController);
    addStudentController.$inject=['entity','addStudentService'];
function addStudentController(entity,addStudentService){
    var vm=this;
    vm.student = entity;
    console.log("sono nel controller dello studente!");
    vm.save = save;
    function save () {
            vm.isSaving = true;
            if (vm.student.id !== null) {
                addStudentService.update(vm.student, onSaveSuccess, onSaveError);
            } else {
                addStudentService.save(vm.student, onSaveSuccess, onSaveError);
            }
        }
        
         function onSaveSuccess (result) {
           /* $scope.$emit('universityApp:studentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;*/
             console.log("Studente SALVATO!");
        }

        function onSaveError () {
            /*vm.isSaving = false;*/
            console.log("ancora cazzi!");
        }
}

})();
