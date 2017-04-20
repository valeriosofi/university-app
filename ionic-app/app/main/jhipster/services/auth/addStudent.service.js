(function () {
    'use strict';

    angular
        .module('main')
        .factory('addStudent', addStudent);

    addStudent.$inject = ['$resource', 'Config'];

    function addStudent ($resource, Config) {
        return $resource(Config.ENV.SERVER_URL + 'api/addStudent', {}, {});
    }
})();
