(function() {
    'use strict';
    angular
        .module('universityApp')
        .factory('StudyGroup', StudyGroup);

    StudyGroup.$inject = ['$resource'];

    function StudyGroup ($resource) {
        var resourceUrl =  'api/study-groups/:id';

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
