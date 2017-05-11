(function() {
    'use strict';
    angular
        .module('main')
        .factory('studyGroupService', studyGroupService);

    studyGroupService.$inject = ['$resource', 'Config'];

    function studyGroupService ($resource, Config) {
        var resourceUrl =  Config.ENV.SERVER_URL + 'api/studyGroup';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
