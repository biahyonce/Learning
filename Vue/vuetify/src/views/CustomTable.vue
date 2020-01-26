<template>
    <div class="dashboard">
        <v-container class="my-5">
            <v-card flat>
                <v-text-field
                        v-model="search"
                        append-icon="search"
                        label="Search"
                        single-line
                        hide-details
                        class="card-item"
                ></v-text-field>

                <v-card
                    class="card-item"
                    v-for="incident in filteredIncidents"
                    :key="incident"
                >
                    <v-layout column wrap :class="`pa-3 incident ${incident.status}`">
                        <v-flex xs12 md6 clas>
                            <div class="caption grey--text">Categoria</div>
                            <div class="caption">{{incident.category}}</div>
                        </v-flex>

                        <v-flex xs6 sm4 md2>
                            <div class="caption grey--text">Last Update</div>
                            <div>{{incident.lastUpdate}}</div>
                        </v-flex>

                        <v-flex xs2 sm4 md2>
                            <div class="caption grey--text">Status</div>
                            <div>{{incident.status}}</div>
                        </v-flex>
                    </v-layout>


                </v-card>
            </v-card>
        </v-container>
    </div>
</template>

<script>
export default {
    name: "CustomTable",
    data: function () {
        return {
            pagination: {},
            search: '',

            incidents: [
                {
                    category: 'Roubo',
                    status: 'complete',
                    lastUpdate: new Date(2020, 1, 16)
                },
                {
                    category: 'Furto',
                    status: 'ongoing',
                    lastUpdate: new Date(2019, 2, 20)
                },
                {
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: new Date(2020, 1, 1)
                }
            ],
        }
    },

    computed: {
        filteredIncidents: function () {
            return this.incidents.filter( (incident) => {
                return incident.category.toUpperCase().match(this.search.toUpperCase())
                    || incident.status.toUpperCase().match(this.search.toUpperCase())
            })
        }
    }
}
</script>

<style scoped>
    .card-item {
        margin-bottom: 8px;
        margin-left: 200px;
        width: 40%;
    }

    .incident.ongoing {
        border-left: 4px solid blue;
    }

    .incident.overdue {
        border-left: 4px solid red;
    }

    .incident.complete {
        border-left: 4px solid green;
    }

</style>