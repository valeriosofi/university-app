(function() {
    'use strict';
    angular
        .module('main')
        .factory('roomService', roomService);

    roomService.$inject = ['$resource'];

    function roomService ($resource) {
        var resourceUrl =  'api/rooms/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', params: {'timeSlot':'8.30-10.00', 'date':'1995-05-14'}, isArray: true},
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
