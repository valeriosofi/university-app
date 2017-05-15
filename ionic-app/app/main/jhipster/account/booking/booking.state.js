(function() {
    'use strict';

    angular
        .module('main')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('booking', {
            parent: 'app',
            url: '/booking',
            data: {
                authorities: ['ROLE_STUDENT'],
                pageTitle: 'booking.title'
            },
            views: {
                'pageContent': {
                    templateUrl: 'main/jhipster/account/booking/booking.html',
                    controller: 'bookingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('booking');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }],
            
                entity: function () {
                    return {
                        date: null,
                        timeSlot: null,
                        id: null
                    };
                }
            }
        });
    }

})();
