<template>
    <div>
        <v-container class="grey lighten-2">
            <v-card>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field
                            v-model="search"
                            append-icon="search"
                            label="Search"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-card-title>

                <v-data-table
                    :headers="headers"
                    :items="incidents"
                    :search="search"
                    class="elevation-1"
                    loading-text="Por favor, aguarde..."
                >
                    <template v-slot:item.status="{ item }">
                        <v-chip :color="getColor(item.status)" dark>{{ item.status }}</v-chip>
                    </template>
                </v-data-table>
            </v-card>
        </v-container>


    </div>
</template>

<script>
    export default {
        data () {
            return {
                search: '',

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
                    {
                        category: 'Atentado Terrorista',
                        status: 'Finalizado',
                        lastUpdate: new Date(2019, 12, 11)
                    },
                ],
            }
        },


        methods: {
            getColor(status) {
                if (status == 'Em andamento') return '#2E86C1'
                else if (status == 'Finalizado') return '#2BBD43'
                else if (status == 'Não atendido') return '#D8231B'
            }
        }
    }
</script>

<style scoped>

</style>