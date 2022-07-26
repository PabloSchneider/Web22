<template>
    {{a.beschreibung}}                    {{a.gebote}} {{a.topgebot}} 
    <button @click="boolforOpenrf = !boolforOpenrf"><p v-if="boolforOpenrf"> ^ </p>
    <p v-else> v </p>
    </button><br>
    
    <div v-if="boolforOpenrf">
        <table>
            <tr>
                <td>Letztes Gebot</td>
                <td>{{a.topgebot}}(Mindestpreis war {{a.mindestpreis}})</td>
            </tr>
            <tr>
                <td>Anholort</td>
                <td>{{a.abholort}}</td>
            </tr>
            <tr>
                <td>bei</td>
                <td>{{a.anbietername}}</td>
            </tr>
            <tr>
                <td>bis</td>
                <td>{{a.ablaufzeitpunkt}}</td>
            </tr>

        </table>
    </div>
</template>

<script setup lang="ts">
    import type {IAngebotListeItem} from '@/services/IAngebotListeItem.js'
    import { ref } from 'vue';

    const props = defineProps<{
        a:IAngebotListeItem 
    }>();
    const boolforOpenrf = ref(false);

</script>
