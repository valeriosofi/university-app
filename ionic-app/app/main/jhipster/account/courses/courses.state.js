(function() {
    'use strict';

    angular
        .module('main')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('courses', {
            parent: 'app',
            url: '/courses',
            data: {
                authorities: ['ROLE_STUDENT'],
                pageTitle: 'courses.title'
            },
            views: {
                'pageContent': {
                    templateUrl: 'main/jhipster/account/courses/courses.html',
                    controller: 'coursesController',
                    controllerAs: 'vm',
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('course');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        });
    }

})();