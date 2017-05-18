(function() {
    'use strict';

    angular
        .module('main')
        .controller('bookingController', bookingController);

    bookingController.$inject = ['$filter', '$window', '$timeout', 'bookingService', 'addStudentService', 'roomService', 'entity', 'DateUtils'];

    function bookingController($filter, $window, $timeout, bookingService, addStudentService, roomService, entity, DateUtils) {
        var vm = this;

        vm.rooms = [];
        vm.booking = entity;
        vm.salvato = true;
        vm.book = false;
        vm.back = back;
        vm.findRooms = findRooms;
        vm.student = [];
        vm.save = save;
        vm.avviso = false;
        
        findGroup();
            
        function findGroup() {
            addStudentService.query(function(result) {
                vm.student = result[0];
            });
        }
        
        function back(){
            vm.salvato = true;
            vm.book = false;
        }
        
        function findRooms(){
            vm.salvato = false;
            loadAll();
            vm.book = true;
            vm.booking.studyGroup = vm.student.studyGroup;
        }

        function loadAll() {
            var date = $filter('date')(vm.booking.date, "yyyy-MM-dd");
            roomService.query({timeSlot: vm.booking.timeSlot, date: date}, function(result) {
                vm.rooms = result;
                vm.searchQuery = null;
            });
        }
        
        function save () {
            vm.isSaving = true;
            if (vm.booking.id !== null) {
                bookingService.update(vm.booking, onSaveSuccess, onSaveError);
            } else {
                bookingService.save(vm.booking, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            vm.book = false;
            vm.avviso = true;
            $timeout(function () {
                $window.location.reload();
            }, 1500);
            
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
