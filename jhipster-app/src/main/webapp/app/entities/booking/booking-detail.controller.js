(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('BookingDetailController', BookingDetailController);

    BookingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Booking', 'StudyGroup', 'Room'];

    function BookingDetailController($scope, $rootScope, $stateParams, previousState, entity, Booking, StudyGroup, Room) {
        var vm = this;

        vm.booking = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('universityApp:bookingUpdate', function(event, result) {
            vm.booking = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
