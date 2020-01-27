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
                        class="default-style"
                ></v-text-field>

                <v-row flat class="default-style">
                    <v-col>
                        <v-switch
                            v-model="switch1"
                            label="ongoing"
                            value="ongoing"
                            color="#2E86C1"
                            flat
                            hide-details
                        />
                    </v-col>

                    <v-col>
                        <v-switch
                                v-model="switch2"
                                label="complete"
                                value="complete"
                                color="#2BBD43"
                                flat
                                hide-details
                        />
                    </v-col>

                    <v-col>
                        <v-switch
                                v-model="switch3"
                                label="overdue"
                                value="overdue"
                                color="#D8231B"
                                flat
                                hide-details
                        />
                    </v-col>


                </v-row>

                <v-expansion-panels class="default-style">
                    <v-expansion-panel
                        v-for="incident in filteredIncidents"
                        :key="incident.id"
                        :class="`pa-3 incident ${incident.status}`"
                    >
                        <v-expansion-panel-header expand-icon="">
                            <v-layout row wrap>
                                <v-flex xs12 md4>
                                    <div class="caption grey--text">Categoria</div>
                                    <div>{{incident.category}}</div>
                                </v-flex>

                                <v-flex xs6 sm4 md4>
                                    <div class="caption grey--text">Last Update</div>
                                    <div>{{incident.lastUpdate}}</div>
                                </v-flex>

                                <v-flex xs2 sm4 md4>
                                    <v-chip :color="getColor(incident.status)" class="white--text">{{incident.status}}</v-chip>
                                </v-flex>

                            </v-layout>
                        </v-expansion-panel-header>

                        <v-expansion-panel-content>
                            <v-layout column wrap>
                                <v-flex xs2 sm4 md2>
                                    <div class="caption grey--text">Descrição</div>
                                    <div class="caption">{{incident.details}}</div>
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
            switch1: 'ongoing',
            switch2: 'complete',
            switch3: 'overdue',

            incidents: [
                {
                    id: 1,
                    category: 'Roubo',
                    status: 'complete',
                    lastUpdate: '2020-01-15 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 2,
                    category: 'Furto',
                    status: 'ongoing',
                    lastUpdate: '2020-01-18 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 3,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                }
            ],
        }
    },

    computed: {
        filteredIncidents: function () {
            const regex = new RegExp(this.switch1 + '|' + this.switch2 + '|'+ this.switch3)

            // First, filter by search
            let filtered = this.incidents.filter( (incident) => {
                return incident.category.toUpperCase().match(this.search.toUpperCase())
            })

            // Then, filter by status using regex
            return filtered.filter( (incident) => {
                return incident.status.match(regex)
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
    .switch {
        width: 10%;
        height: 10%;
    }

    .default-style {
        width: 50%;
        margin-bottom: 8px;
        margin-left: 200px;
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