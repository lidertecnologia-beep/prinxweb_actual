import { create } from "zustand";
import { persist } from "zustand/middleware";

const storeGlobal = create(
  persist(
    (set, get) => ({
      sesion: null,
      setSesion: (data) => set(() => ({ sesion: data })),
      initializeAfterRehydrate: () => {
        const { sesion } = get();
        if (sesion) {
          set(() => ({ sesion: sesion }));
        }
      },
    }),
    {
      name: "su",
      getStorage: () => localStorage,
      onRehydrateStorage: () => (state) => {
        console.log("Estado rehidratado:", state);
      },
    }
  )
);

export default storeGlobal;
