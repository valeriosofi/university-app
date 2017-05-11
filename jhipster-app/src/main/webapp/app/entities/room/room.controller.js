(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('RoomController', RoomController);

    RoomController.$inject = ['Room'];

    function RoomController(Room) {

        var vm = this;

        vm.rooms = [];

        loadAll();

        function loadAll() {
            Room.query(function(result) {
                vm.rooms = result;
                vm.searchQuery = null;
            });
        }
    }
})();
