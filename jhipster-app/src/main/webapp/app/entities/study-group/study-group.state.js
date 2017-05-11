(function() {
    'use strict';

    angular
        .module('universityApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('study-group', {
            parent: 'entity',
            url: '/study-group',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'universityApp.studyGroup.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/study-group/study-groups.html',
                    controller: 'StudyGroupController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('studyGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('study-group-detail', {
            parent: 'study-group',
            url: '/study-group/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'universityApp.studyGroup.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/study-group/study-group-detail.html',
                    controller: 'StudyGroupDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('studyGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'StudyGroup', function($stateParams, StudyGroup) {
                    return StudyGroup.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'study-group',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('study-group-detail.edit', {
            parent: 'study-group-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/study-group/study-group-dialog.html',
                    controller: 'StudyGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StudyGroup', function(StudyGroup) {
                            return StudyGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('study-group.new', {
            parent: 'study-group',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/study-group/study-group-dialog.html',
                    controller: 'StudyGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
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
                    $state.go('study-group', null, { reload: 'study-group' });
                }, function() {
                    $state.go('study-group');
                });
            }]
        })
        .state('study-group.edit', {
            parent: 'study-group',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/study-group/study-group-dialog.html',
                    controller: 'StudyGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StudyGroup', function(StudyGroup) {
                            return StudyGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('study-group', null, { reload: 'study-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('study-group.delete', {
            parent: 'study-group',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/study-group/study-group-delete-dialog.html',
                    controller: 'StudyGroupDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StudyGroup', function(StudyGroup) {
                            return StudyGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('study-group', null, { reload: 'study-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
