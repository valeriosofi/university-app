(function() {
    'use strict';
    angular
        .module('main')
        .factory('roomService', roomService);

    roomService.$inject = ['$resource', 'DateUtils'];

    function roomService ($resource, DateUtils) {
        var resourceUrl =  'api/rooms/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', params: {'timeSlot':'@timeSlot', 'date':'@date'}, isArray: true},
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
