<template>
    <div>
    <v-data-table
        :headers="headers"
        :items="incidents"
        class="elevation-1"
        style="width: 45%"
    >
        <template v-slot:item.status="{ item }">
            <v-chip :color="getColor(item.status)" dark>{{ item.status }}</v-chip>
        </template>
    </v-data-table>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                headers: [
                    {
                        text: 'Category',
                        align: 'left',
                        sortable: false,
                        value: 'category',
                        class: 'header'
                    },
                    {text: 'Status', value: 'status', class: 'header'},
                    {text: 'Last Update', value:'lastUpdate', class:'header'}

                ],

                incidents: [
                    {
                        category: 'Roubo',
                        status: 'Não atendido',
                        lastUpdate: new Date(2020, 1, 16)
                    },
                    {
                        category: 'Furto',
                        status: 'Em andamento',
                        lastUpdate: new Date(2019, 2, 20)
                    },
                    {
                        category: 'Vandalismo',
                        status: 'Em andamento',
                        lastUpdate: new Date(2020, 1, 1)
                    },
                    {
                        category: 'Roubo',
                        status: 'Finalizado',
                        lastUpdate: new Date(2017, 9, 30)
                    },
                    {
                        category: 'Furto',
                        status: 'Não atendido',
                        lastUpdate: new Date(2015, 5, 4)
                    },
                    {
                        category: 'Vandalismo',
                        status: 'Finalizado',
                        lastUpdate: new Date(2019, 11, 27)
                    },
                ]
            }
        },

        methods: {
            getColor(status) {
                if (status == 'Em andamento') return 'blue'
                else if (status == 'Finalizado') return 'green'
                else return 'red'
            }
        }
    }
</script>

<style scoped>
    .header {
        width: 10%;
    }
</style>