(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('StudyGroupDetailController', StudyGroupDetailController);

    StudyGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'StudyGroup', 'Student', 'Booking'];

    function StudyGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, StudyGroup, Student, Booking) {
        var vm = this;

        vm.studyGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('universityApp:studyGroupUpdate', function(event, result) {
            vm.studyGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
