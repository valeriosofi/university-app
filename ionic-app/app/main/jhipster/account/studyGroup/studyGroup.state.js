(function() {
    'use strict';

    angular
        .module('main')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('studyGroup', {
            parent: 'app',
            url: '/studyGroup',
            data: {
                authorities: ['ROLE_STUDENT'],
                pageTitle: 'studyGroup.title'
            },
            views: {
                'pageContent': {
                    templateUrl: 'main/jhipster/account/studyGroup/studyGroup.html',
                    controller: 'studyGroupController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('studyGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }],
                 entity: function () {
                    return {
                        name: null,
                        numMembers: null,
                        id: null
                    };
                }
            }
        })
        .state('studyGroup.new', {
            parent: 'studyGroup',
            url: '/new',
            data: {
                authorities: ['ROLE_STUDENT'],
                pageTitle: 'studyGroup.new'
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'main/jhipster/account/studyGroup/studyGroup-dialog.html',
                    controller: 'studyGroupDialogController',
                    controllerAs: 'vm',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                numMembers: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('studyGroup', null, { reload: 'studyGroup' });
                }, function() {
                    $state.go('studyGroup');
                });
            }]
        });
    }
    
})();
