<template>
    <div class="dashboard">
        <v-container class="grey lighten-2">
            <v-card flat>
                <v-text-field
                        v-model="search"
                        append-icon="search"
                        label="Pesquise uma ocorrência"
                        single-line
                        hide-details
                        class="card-item"
                ></v-text-field>

                <v-expansion-panels class="expansion-panel">
                    <v-expansion-panel
                        v-for="incident in filteredIncidents"
                        :key="incident"
                        :class="`pa-3 incident ${incident.status}`"
                    >
                        <v-expansion-panel-header expand-icon="">
                            <v-layout column wrap>
                                <v-flex xs12 md6 clas>
                                    <div class="caption grey--text">Categoria</div>
                                    <div class="caption">{{incident.category}}</div>
                                </v-flex>

                                <v-flex xs6 sm4 md2>
                                    <div class="caption grey--text">Last Update</div>
                                    <div>{{incident.lastUpdate}}</div>
                                </v-flex>

                                <v-flex xs2 sm4 md2>
                                    <div :class="`caption grey--text header ${incident.status}`">Status</div>
                                    <v-chip :color="getColor(incident.status)" class="white--text">{{incident.status}}</v-chip>
                                </v-flex>

                            </v-layout>
                        </v-expansion-panel-header>

                        <v-expansion-panel-content>
                            <v-layout column wrap>
                                <v-flex xs2 sm4 md2>
                                    <div class="caption grey--text">Descrição</div>
                                    <div>{{incident.details}}</div>
                                </v-flex>
                            </v-layout>
                        </v-expansion-panel-content>

                    </v-expansion-panel>
                </v-expansion-panels>
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
                    lastUpdate: new Date(2020, 1, 16),
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    category: 'Furto',
                    status: 'ongoing',
                    lastUpdate: new Date(2019, 2, 20),
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: new Date(2020, 1, 1),
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
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
    },

    methods: {
        getColor(status) {
            if (status == 'ongoing') return '#2E86C1'
            else if (status == 'complete') return '#2BBD43'
            else if (status == 'overdue') return '#D8231B'
        }
    }
}
</script>

<style scoped>
    .expansion-panel {
        width: 40%;
        margin-bottom: 8px;
        margin-left: 200px;
    }

    .card-item {
        margin-bottom: 8px;
        margin-left: 200px;
        width: 40%;
    }

    .incident.ongoing {
        border-left: 4px solid #2E86C1;
    }

    .incident.overdue {
        border-left: 4px solid #D8231B;
    }

    .incident.complete {
        border-left: 4px solid #2BBD43;
    }
</style>