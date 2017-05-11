(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('BookingController', BookingController);

    BookingController.$inject = ['Booking'];

    function BookingController(Booking) {

        var vm = this;

        vm.bookings = [];

        loadAll();

        function loadAll() {
            Booking.query(function(result) {
                vm.bookings = result;
                vm.searchQuery = null;
            });
        }
    }
})();
